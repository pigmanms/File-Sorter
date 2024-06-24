using System;
using System.IO;
using System.Windows.Forms;

using System;
using System.Windows.Forms;

namespace FileSorter
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Form1());
        }
    }
}


namespace FileSorter
{
    public partial class Form1 : Form
    {
        private string sourceFolderDirectory;
        private string destinationFolderDirectory; 

        public Form1()
        {
            InitializeComponent();
        }

        private void InitializeComponent()
        {
            this.selectSourceFolderButton = new System.Windows.Forms.Button();
            this.selectDestinationFolderButton = new System.Windows.Forms.Button();
            this.runJobButton = new System.Windows.Forms.Button();
            this.sourceFolderLabel = new System.Windows.Forms.Label();
            this.destinationFolderLabel = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // selectSourceFolderButton
            // 
            this.selectSourceFolderButton.Location = new System.Drawing.Point(150, 50);
            this.selectSourceFolderButton.Name = "selectSourceFolderButton";
            this.selectSourceFolderButton.Size = new System.Drawing.Size(200, 23);
            this.selectSourceFolderButton.TabIndex = 0;
            this.selectSourceFolderButton.Text = "Select Source Folder";
            this.selectSourceFolderButton.UseVisualStyleBackColor = true;
            this.selectSourceFolderButton.Click += new System.EventHandler(this.SelectSourceFolderButton_Click);
            // 
            // selectDestinationFolderButton
            // 
            this.selectDestinationFolderButton.Location = new System.Drawing.Point(150, 100);
            this.selectDestinationFolderButton.Name = "selectDestinationFolderButton";
            this.selectDestinationFolderButton.Size = new System.Drawing.Size(200, 23);
            this.selectDestinationFolderButton.TabIndex = 1;
            this.selectDestinationFolderButton.Text = "Select Destination Folder";
            this.selectDestinationFolderButton.UseVisualStyleBackColor = true;
            this.selectDestinationFolderButton.Click += new System.EventHandler(this.SelectDestinationFolderButton_Click);
            // 
            // runJobButton
            // 
            this.runJobButton.Location = new System.Drawing.Point(150, 200);
            this.runJobButton.Name = "runJobButton";
            this.runJobButton.Size = new System.Drawing.Size(200, 23);
            this.runJobButton.TabIndex = 2;
            this.runJobButton.Text = "Run/Execute";
            this.runJobButton.UseVisualStyleBackColor = true;
            this.runJobButton.Click += new System.EventHandler(this.RunJobButton_Click);
            // 
            // sourceFolderLabel
            // 
            this.sourceFolderLabel.AutoSize = true;
            this.sourceFolderLabel.Location = new System.Drawing.Point(150, 80);
            this.sourceFolderLabel.Name = "sourceFolderLabel";
            this.sourceFolderLabel.Size = new System.Drawing.Size(163, 12);
            this.sourceFolderLabel.TabIndex = 3;
            this.sourceFolderLabel.Text = "Source Folder: Not selected";
            // 
            // destinationFolderLabel
            // 
            this.destinationFolderLabel.AutoSize = true;
            this.destinationFolderLabel.Location = new System.Drawing.Point(150, 130);
            this.destinationFolderLabel.Name = "destinationFolderLabel";
            this.destinationFolderLabel.Size = new System.Drawing.Size(185, 12);
            this.destinationFolderLabel.TabIndex = 4;
            this.destinationFolderLabel.Text = "Destination Folder: Not selected";
            // 
            // Form1
            // 
            this.ClientSize = new System.Drawing.Size(500, 300);
            this.Controls.Add(this.destinationFolderLabel);
            this.Controls.Add(this.sourceFolderLabel);
            this.Controls.Add(this.runJobButton);
            this.Controls.Add(this.selectDestinationFolderButton);
            this.Controls.Add(this.selectSourceFolderButton);
            this.Name = "Form1";
            this.Text = "File Sorter v20240503_CE";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        private void SelectSourceFolderButton_Click(object sender, EventArgs e)
        {
            using (FolderBrowserDialog fbd = new FolderBrowserDialog())
            {
                if (fbd.ShowDialog() == DialogResult.OK)
                {
                    sourceFolderDirectory = fbd.SelectedPath;
                    sourceFolderLabel.Text = "Source Folder: " + sourceFolderDirectory;
                }
            }
        }

        private void SelectDestinationFolderButton_Click(object sender, EventArgs e)
        {
            using (FolderBrowserDialog fbd = new FolderBrowserDialog())
            {
                if (fbd.ShowDialog() == DialogResult.OK)
                {
                    destinationFolderDirectory = fbd.SelectedPath;
                    destinationFolderLabel.Text = "Destination Folder: " + destinationFolderDirectory;
                }
            }
        }

        private void RunJobButton_Click(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(sourceFolderDirectory) && !string.IsNullOrEmpty(destinationFolderDirectory))
            {
                SortFiles(new DirectoryInfo(sourceFolderDirectory), new DirectoryInfo(destinationFolderDirectory));
            }
            else
            {
                MessageBox.Show("Please select both source and destination folders.");
            }
        }

        private void SortFiles(DirectoryInfo sourceFolder, DirectoryInfo destinationFolder)
        {
            if (sourceFolder.Exists)
            {
                foreach (FileInfo file in sourceFolder.GetFiles())
                {
                    string destinationSubFolder = null;

                    if (CheckExtension(file.Name, ".pdf", ".doc", ".docx", ".hwp", ".hwpx", ".show", ".cell", ".xlsx", ".ppt", ".pptx", ".xls", ".txt", ".log"))
                    {
                        destinationSubFolder = Path.Combine(destinationFolder.FullName, "Documents");
                    }
                    else if (CheckExtension(file.Name, ".png", ".jpeg", ".jpg", ".webp", ".gif", ".dng"))
                    {
                        destinationSubFolder = Path.Combine(destinationFolder.FullName, "Photos");
                    }
                    else if (CheckExtension(file.Name, ".mp4"))
                    {
                        destinationSubFolder = Path.Combine(destinationFolder.FullName, "Videos");
                    }
                    else if (CheckExtension(file.Name, ".mp3", ".aac", ".wav"))
                    {
                        destinationSubFolder = Path.Combine(destinationFolder.FullName, "Audio");
                    }
                    else if (CheckExtension(file.Name, ".exe", ".jar"))
                    {
                        destinationSubFolder = Path.Combine(destinationFolder.FullName, "Executables");
                    }
                    else if (CheckExtension(file.Name, ".zip"))
                    {
                        destinationSubFolder = Path.Combine(destinationFolder.FullName, "ZipFolders");
                    }

                    if (destinationSubFolder != null)
                    {
                        Directory.CreateDirectory(destinationSubFolder);
                        file.MoveTo(Path.Combine(destinationSubFolder, file.Name));
                    }
                }
                MessageBox.Show("Files sorted successfully.");
            }
            else
            {
                MessageBox.Show("Source folder does not exist or is not a directory.");
            }
        }

        private bool CheckExtension(string fileName, params string[] extensions)
        {
            foreach (string extension in extensions)
            {
                if (fileName.EndsWith(extension, StringComparison.OrdinalIgnoreCase))
                {
                    return true;
                }
            }
            return false;
        }

        private System.Windows.Forms.Button selectSourceFolderButton;
        private System.Windows.Forms.Button selectDestinationFolderButton;
        private System.Windows.Forms.Button runJobButton;
        private System.Windows.Forms.Label sourceFolderLabel;
        private System.Windows.Forms.Label destinationFolderLabel;
    }
}
